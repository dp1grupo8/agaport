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
          controller: 'PuertasMangasZonasCtrl',
          sidebarMeta: {
            order: 300,
          },
        })
        .state('agaport_gestion.puertas_mangas_zonas.nuevo', {
          url: '/nuevo',
          title: 'Puertas, mangas y zonas',
          templateUrl: 'app/pages/AGAPORT_gestion/Puertas_mangas_zonas/puertas_mangas_zonas_nuevo.html',
          controller: 'PuertasMangasZonasNuevoCtrl',
          sidebarMeta: {
            order: 300,
          },
        })
        .state('agaport_gestion.puertas_mangas_zonas.modificar', {
          url: '/modificar',
          title: 'Puertas, mangas y zonas',
          templateUrl: 'app/pages/AGAPORT_gestion/Puertas_mangas_zonas/puertas_mangas_zonas_modificar.html',
          controller: 'PuertasMangasZonasModificarCtrl',
          sidebarMeta: {
            order: 300,
          },
        });
  }

})();