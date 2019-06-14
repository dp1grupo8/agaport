(function () {
  'use strict';

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosCtrl', UsuariosCtrl);

  /** @ngInject */
  function UsuariosCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {

    $scope.smartTablePageSize = 10;
    $scope.usuarioSeleccionado=[];

    $scope.datosUsuarios='';
    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
      $http({
        method:'GET',
        url: globalBackendLink + '/usuarios/listar'
      }).then(function successCallback(response) {
        $scope.datosUsuarios = response.data;
      },function errorCallback(response) {
        console.log('error en obtener usuarios de '+globalBackendLink);
      });
    });

    $http({
      method:'GET',
      url: globalBackendLink + '/usuarios/listar'
    }).then(function successCallback(response) {
      $scope.datosUsuarios = response.data;
    },function errorCallback(response) {
      console.log('error en obtener usuarios de '+globalBackendLink);
    });

    $scope.eliminarUsuario = function(dni) {
      var variable_entrega={"DNI":dni};
      $http({
        method:'POST',
        url: globalBackendLink + '/usuarios/eliminar',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function successCallback(response) {
        console.log("exito");
      },function errorCallback(response) {
        console.log('error en obtener usuarios de '+globalBackendLink);
      });
    };

    editableOptions.theme = 'bs3';
    editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
    editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';

    $scope.open = function (page, size,dni) {
      $uibModal.open({
        controller: 'UsuariosEliminarCtrl',
        animation: true,
        templateUrl: page,
        size: size,
        resolve: {
          usuarioEliminar: function () {
            return $scope.usuarioSeleccionado;
          }
        }
      });
    };
    $scope.openProgressDialog = baProgressModal.open; 

    $scope.seleccionarUsuario = function(usuario){
      $scope.usuarioSeleccionado=usuario;
    };
    
  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosNuevoCtrl', UsuariosNuevoCtrl);

  /** @ngInject */
  function UsuariosNuevoCtrl($scope, $state, $location, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
    console.log('controlador nuevo');
    $scope.usuarioSeleccionada={};

    $http({
        method:'GET',
        url: globalBackendLink + '/permisos/listar'
      }).then(function successCallback(response) {
        $scope.usuarioSelect = response.data;
      },function errorCallback(response) {
        console.log('error en obtener data de usuario de ' + globalBackendLink);
      });


    $scope.registrarUsuario=function(dni,idPermiso,nombres,contrasena){
      var variable_entrega={"DNI":dni,"Password": contrasena,"Nombres": nombres,"idPermiso": idPermiso};

      $http({
        url: globalBackendLink + '/usuarios/insertar',
        method: 'POST',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function() {
          console.log('post usuario success');
          $state.go('agaport_gestion.usuarios');
        },function(response){
          console.log('error POST');
          console.log(response);
          $state.go('agaport_gestion.usuarios');
        });

    }

  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosModificarCtrl', UsuariosModificarCtrl);

  /** @ngInject */
  function UsuariosModificarCtrl($scope, $state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
    console.log('controlador modificar');
    $scope.usuarioSeleccionadoModificar=angular.copy($stateParams);

    $scope.usuarioSeleccionada={};

      //se carga el comboBox
      $http({
        method:'GET',
        url: globalBackendLink + '/permisos/listar'
      }).then(function successCallback(response) {
        $scope.usuarioSelect = response.data;
        //como es modificar, se toma la aerolinea que tiene el id que recibimos. Para eso, es que se utiliza la funcion find()
        $scope.usuarioSeleccionada.selected = $scope.usuarioSelect.find(permiso => permiso.idPermiso==$scope.usuarioSeleccionadoModificar.idPermiso);
      },function errorCallback(response) {
        console.log('error en obtener data de usuarios de ' + globalBackendLink);
      });

    $scope.modificarUsuario= function (dniNuevo,idPermiso,nombresNuevo,contrasenaNuevo){

      var variable_entrega={"DNI":dniNuevo,"Password": contrasenaNuevo,"Nombres": nombresNuevo,"idPermiso": idPermiso};

      $http({
        url: globalBackendLink + '/usuarios/modificar',
        method: 'POST',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(data, status, headers, config) {
        console.log('post usuario success');

        $state.go('agaport_gestion.usuarios');
      }).error(function(data, status, headers, config){
        $state.go('agaport_gestion.usuarios');
      });
    }
  
  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosEliminarCtrl', UsuariosEliminarCtrl);

  /** @ngInject */
  function UsuariosEliminarCtrl($scope, usuarioEliminar, $state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal){
    $scope.confirmarEliminado = function (){
      console.log(usuarioEliminar);
      var variable_entrega={"DNI":usuarioEliminar.dni};

      $http({
        url: globalBackendLink + '/usuarios/eliminar',
        method: 'POST',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(data, status, headers, config) {
        console.log('post puertas success');
        $state.go('agaport_gestion.puertas_mangas_zonas');
      }).error(function(data, status, headers, config){
        console.log("data");
        console.log(data);
        console.log("status");
        console.log(status);
        console.log($uibModal);
        $state.go('agaport_gestion.usuarios');
      });
    }
  }

})();
