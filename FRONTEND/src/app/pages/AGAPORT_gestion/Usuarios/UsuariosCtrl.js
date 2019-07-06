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

    $scope.removeUser = function(index) {
      $scope.users.splice(index, 1);
    };

    $scope.addUser = function() {
      $scope.inserted = {
        id: $scope.users.length+1,
        name: '',
        status: null,
        group: null
      };
      $scope.users.push($scope.inserted);
    };

    $scope.eliminarUsuario = function(dni) {
      var variable_entrega={"DNI":dni};
      $http({
        method:'POST',
        url: globalBackendLink + '/usuarios/eliminar',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        },
        responseType:"json",
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
      }).result.then(function(){
        $http({
          method:'GET',
          url: globalBackendLink + '/usuarios/listar'
        }).then(function successCallback(response) {
          $scope.datosUsuarios = response.data;
        },function errorCallback(response) {
          console.log('error en obtener usuarios de '+globalBackendLink);
        });
      },function(){
        $http({
          method:'GET',
          url: globalBackendLink + '/usuarios/listar'
        }).then(function successCallback(response) {
          $scope.datosUsuarios = response.data;
        },function errorCallback(response) {
          console.log('error en obtener usuarios de '+globalBackendLink);
        });
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
  function UsuariosNuevoCtrl($scope, $state, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
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
        },
        responseType:"json",
      }).then(function() {
          console.log('post usuario success');
          toastr.success("El usuario se registró correctamete");
          $state.go('agaport_gestion.usuarios');
        },function(response){
          if(response && response.data){
            toastr.error("No se pudo almacenar el usuario correctamente. Intente nuevamente", 'Error');
					}
					else{
            console.log(response);
						toastr.error("No se pudo establecer una conexión con el servidor", 'ERROR DEL SISTEMA');
          }

          $state.go('agaport_gestion.usuarios');
        });

    }

  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosModificarCtrl', UsuariosModificarCtrl);

  /** @ngInject */
  function UsuariosModificarCtrl($scope, $state, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal,toastr) {
    console.log('controlador modificar');
    $scope.usuarioSeleccionadoModificar=angular.copy($stateParams);

    $scope.permisoSeleccionada={};

      //se carga el comboBox
      $http({
        method:'GET',
        url: globalBackendLink + '/permisos/listar'
      }).then(function successCallback(response) {
        $scope.permisoSelect = response.data;
        //como es modificar, se toma la aerolinea que tiene el id que recibimos. Para eso, es que se utiliza la funcion find()
        $scope.permisoSeleccionada.selected = $scope.permisoSelect.find(permiso => permiso.idPermiso==$scope.usuarioSeleccionadoModificar.idPermiso);
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
        },
        responseType:"json",
      }).success(function(data, status, headers, config) {
        toastr.success("El usuario se modificó correctamete");
        $state.go('agaport_gestion.usuarios');
      }).error(function(data, status, headers, config){
        toastr.error("No se pudo modificar el usuario correctamente. Intente nuevamente", 'Error');
        $state.go('agaport_gestion.usuarios');
      });
    }
  
  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosEliminarCtrl', UsuariosEliminarCtrl);

  /** @ngInject */
  function UsuariosEliminarCtrl($scope, usuarioEliminar, $state, $filter, editableOptions, editableThemes,$http,$uibModalInstance,baProgressModal,toastr){
    $scope.confirmarEliminado = function (){
      console.log(usuarioEliminar);

        var variable_entrega={"DNI":usuarioEliminar.dni};

        $http({
          url: globalBackendLink + '/usuarios/eliminar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          },
          responseType:"json",
        }).success(function(data, status, headers, config) {
          toastr.success("El usuario se eliminó correctamete");
          $uibModalInstance.close();
          $state.go('agaport_gestion.usuarios');
        }).error(function(data, status, headers, config){
          toastr.error("No se pudo eliminar el usuario correctamente. Intente nuevamente", 'Error');
          $uibModalInstance.close();
        });
    }
  }

})();
