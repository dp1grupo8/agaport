(function () {
    'use strict';
  
    angular.module('Agaport.gestion.aviones')
        .controller('AvionesCtrl', AvionesCtrl);
  
    /** @ngInject */
    function AvionesCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
 	    $scope.avionSeleccionado=[];
      $scope.datosAviones='';           
  
      $http({
        method:'GET',
        url: globalBackendLink + '/aviones/listar'
      }).then(function successCallback(response) {
        $scope.datosAviones = response.data;
      },function errorCallback(response) {
        console.log('error al obtener datos de aviones de ' + globalBackendLink);
      });
  
      $scope.removePuerta = function(index) {
        $scope.users.splice(index, 1);
      };
  
      $scope.addPuerta = function() {
        $scope.inserted = {
          id: $scope.users.length+1,
          name: '',
          status: null,
          group: null
        };
        $scope.users.push($scope.inserted);
      };

      $scope.eliminarUsuario = function(idAvion) {
				var variable_entrega={"idAvion":idAvion};
				$http({
					method:'POST',
					url: globalBackendLink + '/aviones/eliminar',
					data: $.param(variable_entrega),
					headers:{
						'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
					}
				}).then(function successCallback(response) {
					console.log("exito");
				},function errorCallback(response) {
					console.log('error en obtener aviones de '+globalBackendLink);
				});
      };
  
      editableOptions.theme = 'bs3';
      editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
      editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
  
      $scope.open = function (page, size) {
        $uibModal.open({
					controller:'AvionesEliminarCtrl',
          animation: true,
          templateUrl: page,
          size: size,
          resolve: {
            avionEliminar: function () {
              return $scope.avionSeleccionado;
            }
          }
        });
      };
      $scope.openProgressDialog = baProgressModal.open; 
			$scope.seleccionarAvion=function(avion){
				$scope.avionSeleccionado=avion;
			}
    }
	/*cambios desde aqui*/
	angular.module('Agaport.gestion.aviones')
		  .controller('AvionesNuevoCtrl', AvionesNuevoCtrl);

	  /** @ngInject */
	  function AvionesNuevoCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
		console.log('controlador nuevo');
		
		var contro = this;

		$scope.disabled = undefined;
		$scope.hols='hola';

		$scope.standardItem = {};
		$scope.standardSelectItems = [
		  {label: 'Option 1', value: 1},
		  {label: 'Option 2', value: 2},
		  {label: 'Option 3', value: 3},
		  {label: 'Option 4', value: 4}
		];
/*placaAvion,maxPasajero,maxNuevoAv,combNuevoAv,23*/
		$scope.registrarAvion=function(placa,maxpasajeros,cargamax,combMax,idAerolinea){
		  var variable_entrega={"Placa":placa,"CapacidadMax": maxpasajeros,"CargaMax": cargamax,"CombustibleMax": combMax,"idAerolinea":idAerolinea};
		  // $http.post(globalBackendLink+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
		  //   console.log('post usuario success');
		  //   console.log(response);
		  //   $state.go('agaport_gestion.usuarios');
		  // });

		  $http({
				url: globalBackendLink + '/aviones/insertar',
				method: 'POST',
				data: $.param(variable_entrega),
				headers:{
					'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
				}
		  }).then(function() {
				console.log('post avion success');
				$state.go('agaport_gestion.aviones');
		  },function(response){
				console.log('error POST');
				console.log(response);
		  });

		}

	  }

	  angular.module('Agaport.gestion.aviones')
		  .controller('AvionModificarCtrl', AvionModificarCtrl);

	  /** @ngInject */
	  function AvionModificarCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
			console.log('controlador modificar');
			
			var contro = this;

			$scope.disabled = undefined;
			$scope.hols='hola';

			$scope.standardItem = {};
			$scope.standardSelectItems = [
				{label: 'Option 1', value: 1},
				{label: 'Option 2', value: 2},
				{label: 'Option 3', value: 3},
				{label: 'Option 4', value: 4}
			];
	  }

		angular.module('Agaport.gestion.aviones')
			.controller('AvionesEliminarCtrl', AvionesEliminarCtrl);
			
		/** @ngInject */
	  function AvionesEliminarCtrl($scope, avionEliminar, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
			console.log('controlador eliminar');
			
			$scope.confirmarEliminado=function(){
				console.log(avionEliminar);
			}
	  }
		
})(); 
	  

  