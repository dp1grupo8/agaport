(function () {
  'use strict';

  angular.module('Agaport.vistas.asignacion_vuelos', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('agaport_vistas.asignacion_vuelos', {
          url: '/asignacion_vuelos',
          title: 'Asignación de vuelos',
          templateUrl: 'app/pages/AGAPORT_vistas/asignacion_vuelos/asignacion_vuelos.html',
          controller: 'asignacion_vuelosCtrl',
          sidebarMeta: {
            order: 300,
          },
        });
  }

})();