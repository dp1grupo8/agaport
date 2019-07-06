(function () {
    'use strict';
  
    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasCtrl', AerolineasCtrl);
  
    /** @ngInject */
    function AerolineasCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
      $scope.aerolineaSeleccionado=[];
      
      $scope.datosUsuarios='';

      $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
        $http({
          method:'GET',
          url: globalBackendLink + '/aerolineas/listar'
        }).then(function successCallback(response) {
          $scope.datosAerolineas = response.data;
        },function errorCallback(response) {
          console.log('error en obtener data de aerolineas de ' + globalBackendLink);
        });
      });

      $http({
          method:'GET',
          url: globalBackendLink + '/aerolineas/listar'
        }).then(function successCallback(response) {
          $scope.datosAerolineas = response.data;

        },function errorCallback(response) {
          console.log('error en obtener data de aerolineas de ' + globalBackendLink);
      });
  
      editableOptions.theme = 'bs3';
      editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
      editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
		
      $scope.open = function (page, size) {
        $uibModal.open({
          controller: 'AerolineasEliminarCtrl',
          animation: true,
          templateUrl: page,
          size: size,
          resolve: {
            aerolineaBorrar: function () {
              return $scope.aerolineaSeleccionado;
            }
          }
        }).result.then(function(){
          console.log("Se cerro el popup 1");
          $http({
            method:'GET',
            url: globalBackendLink + '/aerolineas/listar'
          }).then(function successCallback(response) {
            $scope.datosAerolineas = response.data;
          },function errorCallback(response) {
            console.log('error en obtener data de aerolineas de ' + globalBackendLink);
          });
        },function(){
          console.log("Se cerro el popup 2");
          $http({
            method:'GET',
            url: globalBackendLink + '/aerolineas/listar'
          }).then(function successCallback(response) {
            $scope.datosAerolineas = response.data;
          },function errorCallback(response) {
            console.log('error en obtener data de aerolineas de ' + globalBackendLink);
          });
        });
      };
      $scope.openProgressDialog = baProgressModal.open;

      $scope.seleccionarAerolinea=function(aerolinea){
        $scope.aerolineaSeleccionado=aerolinea;
      }
	  
    }
	
    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasNuevoCtrl', AerolineasNuevoCtrl);
  
    /** @ngInject */
    function AerolineasNuevoCtrl($scope,$state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
      console.log('controlador nuevo');

      $scope.prioridadSelected={};

      $http({
        method:'GET',
        url: globalBackendLink + '/prioridades/listar'
      }).then(function successCallback(response) {
        $scope.prioridadSelect = response.data;
      },function errorCallback(response) {
        console.log('error en obtener data de prioridades de ' + globalBackendLink);
      });

      $scope.registrarAerolinea=function(nombres,prioridad){
   
        var variable_entrega={"Nombre": nombres,"idPrioridad":prioridad};
        $http({
          url: globalBackendLink  + '/aerolineas/insertar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          },
          responseType:"json",
        }).then(function() {
          toastr.success("La aerolínea se almacenó correctamente");
          $state.go('agaport_gestion.aerolineas');
        },function(response){
          
          if(response && response.data){
						toastr.error("No se pudo almacenar la aerolínea correctamente. Intente nuevamente", 'Error');
					}
					else{
            console.log(response);
						toastr.error("No se pudo establecer una conexión con el servidor", 'ERROR DEL SISTEMA');
          }

          $state.go('agaport_gestion.aerolineas');
        });
      }

    }

    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasModificarCtrl', AerolineasModificarCtrl);
  
    /** @ngInject */
    function AerolineasModificarCtrl($scope, $state, $stateParams,  $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
      console.log('controlador modificar');
      $scope.aerolineaSeleccionadoModificar=angular.copy($stateParams);
      console.log($scope.aerolineaSeleccionadoModificar);

      $scope.prioridadSelected={};

      $http({
        method:'GET',
        url: globalBackendLink + '/prioridades/listar'
      }).then(function successCallback(response) {
        $scope.prioridadSelect = response.data;
        $scope.prioridadSelected.selected = $scope.prioridadSelect.find(prioridad => prioridad.idPrioridad==$scope.aerolineaSeleccionadoModificar.idprioridad);
      },function errorCallback(response) {
        console.log('error en obtener data de prioridades de ' + globalBackendLink);
      });

      $scope.modificarAerolinea= function (idAerolinea,  nombre, idPrioridad){

        var variable_entrega={"idAerolinea":idAerolinea,"Nombre":nombre,"idPrioridad": idPrioridad};

        $http({
          url: globalBackendLink + '/aerolineas/modificar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          },
          responseType:"json",
        }).success(function(data, status, headers, config) {
          toastr.success("La aerolínea se modificó correctamente");
          $state.go('agaport_gestion.aerolineas');
        }).error(function(data, status, headers, config){
          $state.go('agaport_gestion.aerolineas');
        });
      }

    }

    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasEliminarCtrl', AerolineasEliminarCtrl);

    /** @ngInject */
    function AerolineasEliminarCtrl($scope,aerolineaBorrar, $state, $filter, editableOptions, editableThemes,$http,$uibModal,$uibModalInstance,baProgressModal,toastr) {
      console.log('controlador eliminar');

      $scope.confirmarEliminado = function(){
        var variable_entrega={"idAerolinea":aerolineaBorrar.idAerolinea};

        $http({
          url: globalBackendLink + '/aerolineas/eliminar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          },
          responseType:"json",
        }).success(function(data, status, headers, config) {
          toastr.success("La aerolínea se eliminó correctamente");
          $uibModalInstance.close();
          $state.go('agaport_gestion.aerolineas');
        }).error(function(data, status, headers, config){
          $state.go('agaport_gestion.aerolineas');
          $uibModalInstance.close();
        });
      }
    }


  })();
  