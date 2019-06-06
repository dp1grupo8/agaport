(function () {
  'use strict';

  angular.module('Agaport.gestion.aviones', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_gestion.aviones', {
          url: '/aviones',
          title: 'Aviones',
          templateUrl: 'app/pages/AGAPORT_gestion/Aviones/aviones.html',
          controller: 'AvionesCtrl',
          sidebarMeta: {
            order: 100,
          },
        })
        .state('agaport_gestion.aviones.nuevo', {
          url: '/nuevo',
          title: 'Aviones',
          templateUrl: 'app/pages/AGAPORT_gestion/Aviones/aviones_nuevo.html',
          controller: 'AvionesNuevoCtrl',
          sidebarMeta: {
            order: 100,
          },
        })
        .state('agaport_gestion.aviones.modificar', {
          url: '/modificar',
          title: 'Aviones',
          templateUrl: 'app/pages/AGAPORT_gestion/Aviones/aviones_modificar.html',
          controller: 'AvionesCtrl',
          sidebarMeta: {
            order: 100,
          },
        });
  }

  angular.module('Agaport.gestion.aviones')
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

  angular.module('Agaport.gestion.aviones')
        .controller('PuertasMangasZonasModificarCtrl', PuertasMangasZonasModificarCtrl);
  
  /** @ngInject */
  function PuertasMangasZonasModificarCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
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

})();