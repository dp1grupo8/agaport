(function () {
    'use strict';
  
    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasCtrl', PuertasMangasZonasCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
	  $scope.puertaseleccionada=[];
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
  
      $scope.datosPuertas='hola';
  
      var link_header='http://200.16.7.178:8080';      
  
      $http({
        method:'GET',
        url: link_header + '/puertas/listar'
      }).then(function successCallback(response) {
        $scope.datosPuertas = response.data;
      },function errorCallback(response) {
        console.log('error al obtener datos de puertas en ' + link_header);
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
	  $scope.seleccionarPuerta=function(puerta){
		  $scope.puertaseleccionada=puerta;
	  }
    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasNuevoCtrl', PuertasMangasZonasNuevoCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasNuevoCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
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

    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasModificarCtrl', PuertasMangasZonasModificarCtrl);

    /** @ngInject */
    function PuertasMangasZonasModificarCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
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
  
  })();
  