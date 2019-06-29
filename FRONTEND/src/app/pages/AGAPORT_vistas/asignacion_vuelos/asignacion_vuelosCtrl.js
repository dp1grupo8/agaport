(function () {
  'use strict';

  angular.module('Agaport.vistas.asignacion_vuelos')
    .controller('asignacion_vuelosCtrl', asignacion_vuelosCtrl);

  /** @ngInject */
  function asignacion_vuelosCtrl($scope, $http, $timeout) {
    $scope.simulacionIni = false;
    $scope.llamarSimulacion = function () {
      console.log('iniciando simulación 2');
      $http.get('http://200.16.7.178/backendAGAPORT/simulacion/iniciar').then(function successCallback(response) {
        //console.log('simulacion iniciada');
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    $scope.iniciarSimulacion = function () {
      if (!$scope.simulacionIni) {
        //console.log('iniciando simulación 1');
        $scope.simulacionIni = true;
        $scope.llamarSimulacion();
        setInterval($scope.llamarSimulacion, 60000);
      }
    }

    $scope.detenerSimulacion = function () {
      if ($scope.simulacionIni) {
        $scope.simulacionIni = false;
        console.log('detener simulación');
        $http.get('http://200.16.7.178/backendAGAPORT/simulacion/detener').then(function successCallback(response) {
          //console.log('simulacion iniciada');
        }, function errorCallback(response) {
          console.log(response);
        });
      }
    }

    $scope.progressFunction = function () {
      if (!$scope.simulacionIni) {
        return $timeout(function () { }, 3000);
      }
    };
    $scope.progressFunction2 = function () {
      if (!$scope.simulacionIni) {
        return $timeout(function () { }, 0);
      }
    };

    $scope.leerVuelos = function () {
      $http.get('http://200.16.7.178/backendAGAPORT/VuelosLlegada/listar').then(function successCallback(response) {
        $scope.vuelos = response.data;
        for (var i = 0; i < $scope.vuelos.length; i++) {
          //escribir el nombre de la puerta asignada
          switch ($scope.vuelos[i].puerta.tipo) {
            case 0:
              $scope.vuelos[i].strPuerta = 'Zona ' + $scope.vuelos[i].puerta.idPuerta;
              break;
            case 1:
              $scope.vuelos[i].strPuerta = 'Gate ' + $scope.vuelos[i].puerta.idPuerta;
              break;
          }
          //escribir el estado del vuelo
          switch ($scope.vuelos[i].estado) {
            case 2:
              $scope.vuelos[i].strEstado = 'En camino';
              break;
            case 3:
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
    //     switch ($scope.vuelos[i].puerta.tipo) {
    //       case 0:
    //         $scope.vuelos[i].strPuerta = 'Gate ' + $scope.vuelos[i].puerta.idPuerta;
    //         break;
    //       case 1:
    //         $scope.vuelos[i].strPuerta = 'Zona ' + $scope.vuelos[i].puerta.idPuerta;
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