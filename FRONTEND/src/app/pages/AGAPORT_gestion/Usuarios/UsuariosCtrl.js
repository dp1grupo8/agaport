(function () {
  'use strict';

  angular.module('Agaport.gestion.usuarios')
      .controller('UsuariosCtrl', UsuariosCtrl);

  /** @ngInject */
  function UsuariosCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {

    $scope.smartTablePageSize = 10;
    $scope.usuarioSeleccionado=[];

    $scope.smartTableData = [
      {
        id: 1,
        firstName: '77591264',
        lastName: 'Otto',
        username: 'Operador',
        email: 'mdo@gmail.com',
        age: '28'
      },
      {
        id: 2,
        firstName: '12345678',
        lastName: 'Thornton',
        username: 'Operador',
        email: 'fat@yandex.ru',
        age: '45'
      },
      {
        id: 3,
        firstName: '65945632',
        lastName: 'Bird',
        username: 'Operador',
        email: 'twitter@outlook.com',
        age: '18'
      },
      {
        id: 4,
        firstName: '65923545',
        lastName: 'Snow',
        username: 'Operador',
        email: 'snow@gmail.com',
        age: '20'
      },
      {
        id: 5,
        firstName: '35659858',
        lastName: 'Sparrow',
        username: 'Operador',
        email: 'jack@yandex.ru',
        age: '30'
      },
      {
        id: 6,
        firstName: '08063264',
        lastName: 'Smith',
        username: 'Operador',
        email: 'ann@gmail.com',
        age: '21'
      },
      {
        id: 7,
        firstName: '10652154',
        lastName: 'Black',
        username: 'Operador',
        email: 'barbara@yandex.ru',
        age: '43'
      },
      
    ];

    $scope.datosUsuarios='hola';

    var link_header='http://200.16.7.178:8080';

    $http({
      method:'GET',
      url: link_header + '/usuarios/listar'
    }).then(function successCallback(response) {
      $scope.datosUsuarios = response.data;
    },function errorCallback(response) {
      console.log('error en obtener usuarios de '+link_header);
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
        url: link_header + '/usuarios/eliminar',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function successCallback(response) {
        console.log("exito");
      },function errorCallback(response) {
        console.log('error en obtener usuarios de '+link_header);
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
  function UsuariosNuevoCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
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
      var link_header='http://200.16.7.178:8080';
      var variable_entrega={"DNI":dni,"Password": contrasena,"Nombres": nombres,"idPermiso": idPermiso};
      // $http.post(link_header+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
      //   console.log('post usuario success');
      //   console.log(response);
      //   $state.go('agaport_gestion.usuarios');
      // });

      $http({
        url: link_header + '/usuarios/insertar',
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
