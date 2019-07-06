(function () {
  'use strict';

  angular.module('BlurAdmin.pages.mi_pagina', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('mi_pagina', {
          url: '/mi_pagina',
          templateUrl: 'app/pages/AGAPORT_mi_pagina/mi_pagina.html',
          controller: 'mi_paginaCtrl',
          title: 'Página principal',
          sidebarMeta: {
            icon: 'ion-android-home',
            order: 1000,
          },
        });
  }

})();
