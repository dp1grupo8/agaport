(function () {
  'use strict';

  angular.module('Agaport.vistas.estado_puertas')
    .controller('estado_puertasCtrl', estado_puertasCtrl);

  /** @ngInject */
  function estado_puertasCtrl($scope, $http) {

    $scope.range = function (num) {
      return new Array(num);
    }

    //Lista de: 1,5,8,30 (puertas de vuelos que vienen), puertas habilitadas no en uso: 3,9,12,33
    //data importante para el estado
    /**
     * JSON: VueloLlegada.json
     * 
        * -estado //0: programado(vuelos en la cola de asignación). 2:asignado 3:aterrizado 4:muerto (vuelos que ya han liberado la puerta)
        *           Solo se obtendrán vuelos en estado 2 y 3
        * -Objeto: Puerta
        *      -idPuerta: corresponde al numero del gate/zona
        *      -distanciaSalida
        *      -flujoPersonas
        *      -tipo: 0:manga(gate), 1:zona
        *      -estado: 0: deshabilitada. 1: habilitada. 2: en uso. 3: programada
        * -Objeto: Avion
        *      -placa
        *      -aerolinea
        * -clase: clase del vuelo (string)
    */
    $http.get('/app/pages/AGAPORT_vistas/estado_puertas/data/puertas.json').then(function successCallback(response) {
      $scope.puertas = response.data;
      var puertas = $scope.puertas
      console.log(puertas);
      //no itera objetos
      for (var puerta in puertas) {
        var tipo = '';
        console.log(puerta)
        // if(puerta.tipo = 0){
        //   tipo = 'gate';
        // }
        // var divId = tipo + '-' + puerta.idPuerta;
        // if (puerta.estado = 1) {
        //   document.getElementById(divId).style.backgroundColor = "blue"
        // }
      }
    }, function errorCallback(response) {
      console.log(response);
    });
    $http.get('/app/pages/AGAPORT_vistas/estado_puertas/data/vuelos-llegada.json').then(function successCallback(response) {
      $scope.vuelos = response.data;
      console.log(response.data);
    }, function errorCallback(response) {
      console.log(response);
    });

    $scope.detalle = function (tipo, puerta) {
      console.log(tipo, puerta);
    }
  }

})();