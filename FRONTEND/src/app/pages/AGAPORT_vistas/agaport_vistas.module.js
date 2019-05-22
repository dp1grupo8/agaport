(function () {
  'use strict';

  angular.module('Agaport.vistas', [
      'Agaport.vistas.asignacion_vuelos',
      'Agaport.vistas.estado_puertas',
      'Agaport.vistas.historico_mangas_zonas',
    ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_vistas', {
          url: '/agaport_vistas',
          template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
          abstract: true,
          title: 'Vistas',
          sidebarMeta: {
            icon: 'ion-android-laptop',
            order: 1000,
          },
        });
  }

})();
