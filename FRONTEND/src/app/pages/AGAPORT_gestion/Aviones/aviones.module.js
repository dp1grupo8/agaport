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
        })
        .state('agaport_gestion.aviones.nuevo', {
          url: '/nuevo',
          title: 'Aviones',
          templateUrl: 'app/pages/AGAPORT_gestion/Aviones/aviones_nuevo.html',
          controller: 'AvionesNuevoCtrl',
          sidebarMeta: {
            order: 100,
          },
        })
        .state('agaport_gestion.aviones.modificar', {
          url: '/modificar',
          params:{
            idAvion:null,
            placa: null,
            capacidadMax: null,
            cargaMax:null,
            combustibleMax:null,
            idAerolinea:null,
          },
          title: 'Modificar Aviones',
          templateUrl: 'app/pages/AGAPORT_gestion/Aviones/aviones_modificar.html',
          controller: 'AvionModificarCtrl',
          sidebarMeta: {
            order: 100,
          },
        });
  }

})();