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
          controller: 'UsuariosCtrl',
          sidebarMeta: {
            icon: 'ion-android-laptop',
            order: 400,
          },
        }).state('agaport_gestion.usuarios.nuevo', {
          url: '/nuevo',
          title: 'Nuevo usuario',
          templateUrl: 'app/pages/AGAPORT_gestion/Usuarios/usuarios_nuevo.html',
        }).state('agaport_gestion.usuarios.modificar', {
          url: '/modificar',
          title: 'Modificar usuario',
          templateUrl: 'app/pages/AGAPORT_gestion/Usuarios/usuarios_modificar.html',
        });
  }

})();