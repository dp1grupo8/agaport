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
    // $('document').ready(function () {
    //   var div_zonas = document.getElementsByClassName("zona");
    //   for (var i = 0; i < div_zonas.length; i++) {
    //     div_zonas[i].style.backgroundColor = "#E9E9E9";
    //   }
    //   var div_gates = document.getElementsByClassName("gate");
    //   for (var i = 0; i < div_gates.length; i++) {
    //     div_gates[i].style.backgroundColor = "#E9E9E9";
    //   }
    // });
    $http.get('/app/pages/AGAPORT_vistas/estado_puertas/data/puertas.json').then(function successCallback(response) {
      var div_zonas = document.getElementsByClassName("zona");
      for (var i = 0; i < div_zonas.length; i++) {
        div_zonas[i].style.backgroundColor = "#E9E9E9";
      }
      var div_gates = document.getElementsByClassName("gate");
      for (var i = 0; i < div_gates.length; i++) {
        div_gates[i].style.backgroundColor = "#E9E9E9";
      }
      $scope.puertas = response.data;
      var puertas = $scope.puertas;
      var i;
      for (i = 0; i < puertas.length; i++) {
        var puerta = puertas[i].Puerta;
        var tipo;
        if (puerta.tipo == 0) {
          tipo = 'gate';
        } else {
          tipo = 'zona';
        }
        var divId = tipo + '-' + puerta.idPuerta;
        if (puerta.estado == 1) {
          document.getElementById(divId).style.backgroundColor = "#FFFFFF"
        }
      }
    }, function errorCallback(response) {
      // console.log(response);
    });
    $http.get('/app/pages/AGAPORT_vistas/estado_puertas/data/vuelos-llegada.json').then(function successCallback(response) {
      var div_zonas = document.getElementsByClassName("zona");
      for (var i = 0; i < div_zonas.length; i++) {
        div_zonas[i].style.backgroundColor = "#E9E9E9";
      }
      var div_gates = document.getElementsByClassName("gate");
      for (var i = 0; i < div_gates.length; i++) {
        div_gates[i].style.backgroundColor = "#E9E9E9";
      }
      $scope.vuelos = response.data;
      var vuelos = $scope.vuelos;
      var i;
      for (i = 0; i < vuelos.length; i++) {
        var puerta = vuelos[i].Puerta;
        var tipo;
        if (puerta.tipo == 0) {
          tipo = 'gate';
        } else {
          tipo = 'zona';
        }
        var divId = tipo + '-' + puerta.idPuerta;
        if (puerta.estado == 2) {
          var src = document.getElementById(divId);
          src.style.backgroundColor = "#F1C232";
          //agregar icono de avion          
          var img = document.createElement("img");
          var width = Math.floor(src.offsetWidth*0.5);
          console.log(width);
          img.width = width;
          img.height = width;
          img.src = "/../../../../assets/pictures/aiga_departingflights-512.png";          

          src.appendChild(img);
        } else {
          document.getElementById(divId).style.backgroundColor = "#F1C232";
        }
      }
    }, function errorCallback(response) {
      console.log(response);
    });

    $scope.detalle = function (tipo, puerta) {
      console.log(tipo, puerta);
    }
  }

})();