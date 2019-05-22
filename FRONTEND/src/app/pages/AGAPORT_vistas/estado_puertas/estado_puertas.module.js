(function () {
  'use strict';

  angular.module('Agaport.vistas.estado_puertas', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_vistas.estado_puertas', {
          url: '/estado_puertas',
          title: 'Estado de puertas',
          templateUrl: 'app/pages/AGAPORT_vistas/estado_puertas/estado_puertas.html',
          //controller: 'ProfilePageCtrl',
          sidebarMeta: {
            order: 300,
          },
        });
  }

})();