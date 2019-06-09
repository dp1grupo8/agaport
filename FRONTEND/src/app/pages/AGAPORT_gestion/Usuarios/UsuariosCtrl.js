(function () {
  'use strict';

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosCtrl', UsuariosCtrl);

  /** @ngInject */
  function UsuariosCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {

    $scope.smartTablePageSize = 10;
    $scope.usuarioSeleccionado=[];

    $scope.datosUsuarios='';

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

    $scope.open = function (page, size) {
      $uibModal.open({
        animation: true,
        templateUrl: page,
        size: size,
        resolve: {
          items: function () {
            return $scope.items;
          }
        }
      });
    };
    $scope.openProgressDialog = baProgressModal.open; 

    $scope.seleccionarUsuario = function(usuario){
      $scope.usuarioSeleccionado=usuario;
    }
  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosNuevoCtrl', UsuariosNuevoCtrl);

  /** @ngInject */
  function UsuariosNuevoCtrl($scope, $location, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
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

    $scope.registrarUsuario=function(dni,idPermiso,nombres,contrasena){
      var globalBackendLink='http://200.16.7.178:8080';
      var variable_entrega={"DNI":dni,"Password": contrasena,"Nombres": nombres,"idPermiso": idPermiso};
      // $http.post(globalBackendLink+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
      //   console.log('post usuario success');
      //   console.log(response);
      //   $state.go('agaport_gestion.usuarios');
      // });

      $http({
        url: globalBackendLink + '/usuarios/insertar',
        method: 'POST',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(data, status, headers, config) {
        console.log('post usuario success');
        $state.go('agaport_gestion.usuarios');
      }).error(function(data, status, headers, config){
        console.log("data");
        console.log(data);
        console.log("status");
        console.log(status);
        //$state.go('agaport_gestion.usuarios');
        
      });

    }

  }

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosModificarCtrl', UsuariosModificarCtrl);

  /** @ngInject */
  function UsuariosModificarCtrl($scope, $stateParams, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
    console.log('controlador modificar');
    var usuarioSeleccionado=$stateParams;
    console.log(usuarioSeleccionado);
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

})();
