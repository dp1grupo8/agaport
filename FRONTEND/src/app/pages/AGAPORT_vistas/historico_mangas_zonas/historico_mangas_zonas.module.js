(function () {
  'use strict';

  angular.module('Agaport.vistas.historico_mangas_zonas', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_vistas.historico_mangas_zonas', {
          url: '/historico_mangas_zonas',
          title: 'Histórico de mangas y zonas',
          templateUrl: 'app/pages/AGAPORT_vistas/historico_mangas_zonas/historico_mangas_zonas.html',
          //controller: 'ProfilePageCtrl',
          sidebarMeta: {
            order: 300,
          },
        });
  }

})();