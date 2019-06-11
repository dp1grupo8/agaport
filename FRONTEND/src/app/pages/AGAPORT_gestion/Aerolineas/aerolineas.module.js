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
          controller: 'AerolineasCtrl',
          sidebarMeta: {
            order: 200,
          },
        })
        .state('agaport_gestion.aerolineas.nuevo', {
          url: '/nuevo',
          title: 'Aerolíneas',
          templateUrl: 'app/pages/AGAPORT_gestion/Aerolineas/aerolineas_nuevo.html',
          controller: 'AerolineasNuevoCtrl',
        })
        .state('agaport_gestion.aerolineas.modificar', {
          url: '/modificar',
          params:{
            nombreaerolinea: null,
            prioridad: null,
            maxpasajeros:null,
          },
          title: 'Aerolíneas',
          templateUrl: 'app/pages/AGAPORT_gestion/Aerolineas/aerolineas_modificar.html',
          controller: 'AerolineasModificarCtrl',
        });
  }

})();