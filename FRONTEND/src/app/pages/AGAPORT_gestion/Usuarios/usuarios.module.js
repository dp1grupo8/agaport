(function () {
  'use strict';

  angular.module('Agaport.gestion.usuarios', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('agaport_gestion.usuarios', {
          url: '/usuarios',
          title: 'Usuarios',
          templateUrl: 'app/pages/AGAPORT_gestion/Usuarios/usuarios.html',
          //controller: 'ProfilePageCtrl',
          sidebarMeta: {
            icon: 'ion-android-laptop',
            order: 400,
          },
        });
  }

})();