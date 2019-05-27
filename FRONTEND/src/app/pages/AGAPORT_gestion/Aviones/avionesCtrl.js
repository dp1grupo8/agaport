(function () {
    'use strict';
  
    angular.module('Agaport.gestion.aviones')
        .controller('AvionesCtrl', AvionesCtrl);
  
    /** @ngInject */
    function AvionesCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
  
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
      console.log('inicio');
      
  
      $http({
        method:'GET',
        url: link_header + '/agaport/usuarios/listar'
      }).then(function successCallback(response) {
        $scope.datosUsuarios = response;
        console.log('exito');
        console.log(response);
      },function errorCallback(response) {
        console.log('error');
        console.log(response);
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
    }
  
  })();
  