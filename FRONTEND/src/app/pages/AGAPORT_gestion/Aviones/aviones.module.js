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
        });
  }

})();