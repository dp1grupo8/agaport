(function () {
  'use strict';

  angular.module('Agaport.gestion.puertas_mangas_zonas', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_gestion.puertas_mangas_zonas', {
          url: '/puertas_mangas_zonas',
          title: 'Puertas, mangas y zonas',
          templateUrl: 'app/pages/AGAPORT_gestion/Puertas_mangas_zonas/puertas_mangas_zonas.html',
          //controller: 'ProfilePageCtrl',
          sidebarMeta: {
            order: 300,
          },
        });
  }

})();