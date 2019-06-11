(function () {
  'use strict';

  angular.module('Agaport.gestion', [
      'ui.select',
      'ngSanitize',
      'ui.router',
      'Agaport.gestion.aerolineas',
      'Agaport.gestion.aviones',
      'Agaport.gestion.puertas_mangas_zonas',
      'Agaport.gestion.usuarios',
    ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_gestion', {
          url: '/agaport_gestion',
          template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
          abstract: true,
          title: 'Gestión',
          sidebarMeta: {
            icon: 'ion-gear-a',
            order: 1000,
          },
        });
  }

})();
