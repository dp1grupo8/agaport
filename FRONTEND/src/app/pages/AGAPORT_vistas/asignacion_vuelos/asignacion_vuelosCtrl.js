(function () {
  'use strict';

  angular.module('Agaport.vistas.asignacion_vuelos')
    .controller('asignacion_vuelosCtrl', asignacion_vuelosCtrl);

  /** @ngInject */
  function asignacion_vuelosCtrl($scope, $http) {

    $scope.leerVuelos = function () {
      $http.get('/app/pages/AGAPORT_vistas/asignacion_vuelos/data/vuelos-llegada.json').then(function successCallback(response) {
        $scope.vuelos = response.data;
        for (var i = 0; i < $scope.vuelos.length; i++) {
          //escribir el nombre de la puerta asignada
          switch ($scope.vuelos[i].Puerta.tipo) {
            case 0:
              $scope.vuelos[i].strPuerta = 'Gate ' + $scope.vuelos[i].Puerta.idPuerta;
              break;
            case 1:
              $scope.vuelos[i].strPuerta = 'Zona ' + $scope.vuelos[i].Puerta.idPuerta;
              break;
          }
          //escribir el estado del vuelo
          switch ($scope.vuelos[i].estado) {
            case 0:
              $scope.vuelos[i].strEstado = 'En camino';
              break;
            case 1:
              $scope.vuelos[i].strEstado = 'Aterrizado';
              break;
          }
        }
        console.log($scope.vuelos);
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    $scope.leerVuelos();
    setInterval($scope.leerVuelos, 30000);

    // $http.get('/app/pages/AGAPORT_vistas/asignacion_vuelos/data/vuelos-llegada.json').then(function successCallback(response) {
    //   $scope.vuelos = response.data;
    //   console.log($scope.vuelos);
    //   for (var i = 0; i < $scope.vuelos.length; i++) {
    //     //escribir el nombre de la puerta asignada
    //     switch ($scope.vuelos[i].Puerta.tipo) {
    //       case 0:
    //         $scope.vuelos[i].strPuerta = 'Gate ' + $scope.vuelos[i].Puerta.idPuerta;
    //         break;
    //       case 1:
    //         $scope.vuelos[i].strPuerta = 'Zona ' + $scope.vuelos[i].Puerta.idPuerta;
    //         break;
    //     }
    //     //escribir el estado del vuelo
    //     switch ($scope.vuelos[i].estado) {
    //       case 0:
    //         $scope.vuelos[i].strEstado = 'En camino';
    //         break;
    //       case 1:
    //         $scope.vuelos[i].strEstado = 'Aterrizado';
    //         break;
    //     }
    //   }
    //   console.log($scope.vuelos);
    // }, function errorCallback(response) {
    //   console.log(response);
    // });
  }

})();