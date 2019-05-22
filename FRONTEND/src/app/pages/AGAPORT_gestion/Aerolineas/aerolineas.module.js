(function () {
  'use strict';

  angular.module('Agaport.gestion.aerolineas', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_gestion.aerolineas', {
          url: '/aerolineas',
          title: 'Aerolíneas',
          templateUrl: 'app/pages/AGAPORT_gestion/Aerolineas/aerolineas.html',
          //controller: 'ProfilePageCtrl',
          sidebarMeta: {
            order: 200,
          },
        });
  }

})();