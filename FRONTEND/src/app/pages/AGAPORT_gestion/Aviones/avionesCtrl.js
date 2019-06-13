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
			$scope.aerolineaSeleccionada={};
			//se carga el comboBox
			$http({
				method:'GET',
				url: globalBackendLink + '/aerolineas/listar'
			}).then(function successCallback(response) {
				$scope.aerolineasSelect = response.data;
			},function errorCallback(response) {
				console.log('error en obtener data de aerolineas de ' + globalBackendLink);
			});

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
	  function AvionModificarCtrl($scope,$state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
			console.log('controlador modificar');
			$scope.avionSeleccionadoModificar=angular.copy($stateParams);
			console.log($scope.avionSeleccionadoModificar);

			$scope.aerolineaSeleccionada={};

			//se carga el comboBox
			$http({
				method:'GET',
				url: globalBackendLink + '/aerolineas/listar'
			}).then(function successCallback(response) {
				$scope.aerolineasSelect = response.data;
				//como es modificar, se toma la aerolinea que tiene el id que recibimos. Para eso, es que se utiliza la funcion find()
				$scope.aerolineaSeleccionada.selected = $scope.aerolineasSelect.find(aerolinea => aerolinea.idAerolinea==$scope.avionSeleccionadoModificar.idAerolinea);
			},function errorCallback(response) {
				console.log('error en obtener data de aerolineas de ' + globalBackendLink);
			});

	    $scope.modificarAvion= function (idAvion,placa,capacidadMax,cargaMax,combustibleMax,idAerolinea){
	      var variable_entrega={"idAvion":idAvion,"Placa": placa,"CapacidadMax": capacidadMax,"CargaMax": cargaMax,"CombustibleMax":combustibleMax,"idAerolinea":idAerolinea};
	      console.log("se envia la variable ");
	      console.log(variable_entrega);
	      $http({
	        url: globalBackendLink + '/aviones/modificar',
	        method: 'POST',
	        data: $.param(variable_entrega),
	        headers:{
	          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
	        }
	      }).success(function(data, status, headers, config) {
	        console.log('post aviones success');

	        $state.go('agaport_gestion.aviones');
	      }).error(function(data, status, headers, config){
	        $state.go('agaport_gestion.aviones');
				});
				
				console.log($scope.aerolineaSeleccionada.selected);
	    }

	  }

		angular.module('Agaport.gestion.aviones')
			.controller('AvionesEliminarCtrl', AvionesEliminarCtrl);
			
		/** @ngInject */
	  function AvionesEliminarCtrl($scope, avionEliminar, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
			console.log('controlador eliminar');
			
			$scope.confirmarEliminado=function(){
				console.log(avionEliminar);

				var variable_entrega={"idAvion":avionEliminar.idAvion};

        $http({
          url: globalBackendLink + '/aviones/eliminar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).success(function(data, status, headers, config) {
          console.log('post aviones success');
  
          $state.go('agaport_gestion.usuarios');
        }).error(function(data, status, headers, config){
          console.log("data");
          console.log(data);
          console.log("status");
          console.log(status);
          console.log($uibModal);
          $state.go('agaport_gestion.aviones');
        });
			}
	  }
		
})(); 
	  

  