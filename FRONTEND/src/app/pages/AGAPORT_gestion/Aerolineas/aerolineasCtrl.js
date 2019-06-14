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
    function AerolineasNuevoCtrl($scope,$state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador nuevo');

      $scope.registrarAerolinea=function(nombres,prioridad){
   
        var variable_entrega={"Nombre": nombres,"idPrioridad":prioridad};
        // $http.post(link_header+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
        //   console.log('post usuario success');
        //   console.log(response);
        //   $state.go('agaport_gestion.usuarios');
        // });
        $http({
          url: globalBackendLink  + '/aerolineas/insertar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).then(function() {
          console.log('post aerolinea success');
          $state.go('agaport_gestion.aerolineas');
        },function(response){
          console.log('error POST');
          console.log(response);
          $state.go('agaport_gestion.aerolineas');
        });
      }

    }

    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasModificarCtrl', AerolineasModificarCtrl);
  
    /** @ngInject */
    function AerolineasModificarCtrl($scope, $state, $stateParams,  $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador modificar');
      $scope.aerolineaSeleccionadoModificar=angular.copy($stateParams);
      console.log($scope.aerolineaSeleccionadoModificar);



      $scope.modificarAerolinea= function (idAerolinea,  nombre, idPrioridad){

        var variable_entrega={"idAerolinea":idAerolinea,"Nombre":nombre,"idPrioridad": idPrioridad};

        $http({
          url: globalBackendLink + '/aerolineas/modificar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).success(function(data, status, headers, config) {
          console.log('post aerolinea success');
          $state.go('agaport_gestion.aerolineas');
        }).error(function(data, status, headers, config){
          $state.go('agaport_gestion.aerolineas');
        });
      }
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

    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasEliminarCtrl', AerolineasEliminarCtrl);

    /** @ngInject */
    function AerolineasEliminarCtrl($scope,aerolineaBorrar, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador eliminar');

      $scope.confirmarEliminado = function(){
        var variable_entrega={"idAerolinea":aerolineaBorrar.idAerolinea};

        $http({
          url: globalBackendLink + '/aerolineas/eliminar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).success(function(data, status, headers, config) {
          console.log('post aerolineas success');
  
          $state.go('agaport_gestion.aerolineas');
        }).error(function(data, status, headers, config){
          console.log("data");
          console.log(data);
          console.log("status");
          console.log(status);
          console.log($uibModal);
          $state.go('agaport_gestion.aerolineas');
        });
      }
    }


  })();
  