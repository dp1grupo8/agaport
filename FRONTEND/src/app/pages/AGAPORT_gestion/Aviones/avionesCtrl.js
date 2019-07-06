(function () {
    'use strict';
  
    angular.module('Agaport.gestion.aviones')
        .controller('AvionesCtrl', AvionesCtrl);
  
    /** @ngInject */
    function AvionesCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
 	    $scope.avionSeleccionado=[];
			$scope.datosAviones='';
			
			$scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
        $http({
					method:'GET',
					url: globalBackendLink + '/aviones/listar'
				}).then(function successCallback(response) {
					$scope.datosAviones = response.data;
				},function errorCallback(response) {
					console.log('error al obtener datos de aviones de ' + globalBackendLink);
				});
      });
  
      $http({
        method:'GET',
        url: globalBackendLink + '/aviones/listar'
      }).then(function successCallback(response) {
        $scope.datosAviones = response.data;
      },function errorCallback(response) {
        console.log('error al obtener datos de aviones de ' + globalBackendLink);
      });

      $scope.eliminarUsuario = function(idAvion) {
				var variable_entrega={"idAvion":idAvion};
				$http({
					method:'POST',
					url: globalBackendLink + '/aviones/eliminar',
					data: $.param(variable_entrega),
					headers:{
						'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
					},
				}).then(function successCallback(response) {
					console.log("ejkiminar raraso");
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
        }).result.then(function(){
					$http({
						method:'GET',
						url: globalBackendLink + '/aviones/listar'
					}).then(function successCallback(response) {
						$scope.datosAviones = response.data;
					},function errorCallback(response) {
						console.log('error al obtener datos de aviones de ' + globalBackendLink);
					});
				},function(){
					$http({
						method:'GET',
						url: globalBackendLink + '/aviones/listar'
					}).then(function successCallback(response) {
						$scope.datosAviones = response.data;
					},function errorCallback(response) {
						console.log('error al obtener datos de aviones de ' + globalBackendLink);
					});
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
	  function AvionesNuevoCtrl($scope, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
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
					},
					responseType:"json",
				}).then(function() {
					toastr.success("El avión se almacenó correctamente");
					$state.go('agaport_gestion.aviones');
				},function(response){
					if(response && response.data){
						toastr.error("No se pudo almacenar el avión correctamente. Intente nuevamente", 'Error');
					}
					else{
						console.log(response);
						toastr.error("No se pudo establecer una conexión con el servidor", 'ERROR DEL SISTEMA');
					}

					$state.go('agaport_gestion.aviones');
				});
			}

	  }

	  angular.module('Agaport.gestion.aviones')
		  .controller('AvionModificarCtrl', AvionModificarCtrl);

	  /** @ngInject */
	  function AvionModificarCtrl($scope,$state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
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
					},
					responseType:"json",
	      }).success(function(data, status, headers, config) {
	        toastr.success("El avión se modificó correctamente");
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
	  function AvionesEliminarCtrl($scope, avionEliminar, $state, $filter, editableOptions, editableThemes,$http,$uibModal,$uibModalInstance,baProgressModal,toastr) {
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
					},
					responseType:"json",
        }).success(function(data, status, headers, config) {
					toastr.success("El avión se eliminó correctamente");
					$uibModalInstance.close();
          $state.go('agaport_gestion.aviones');
        }).error(function(data, status, headers, config){
					$state.go('agaport_gestion.aviones');
					$uibModalInstance.close();
        });
			}
	  }
		
})(); 
	  

  