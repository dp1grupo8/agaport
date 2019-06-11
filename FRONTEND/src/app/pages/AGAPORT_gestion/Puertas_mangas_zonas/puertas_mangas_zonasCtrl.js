(function () {
    'use strict';
  
    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasCtrl', PuertasMangasZonasCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
	    $scope.puertaseleccionada=[];
      $scope.datosPuertas='';
  
      $http({
        method:'GET',
        url: globalBackendLink + '/puertas/listar'
      }).then(function successCallback(response) {
        $scope.datosPuertas = response.data;
      },function errorCallback(response) {
        console.log('error al obtener datos de puertas en ' + globalBackendLink);
      });
  
      $scope.removePuerta = function(index) {
        $scope.users.splice(index, 1);
      };
  
      $scope.addPuerta = function() {
        $scope.inserted = {
          id: $scope.users.length+1,
          name: '',
          status: null,
          group: null
        };
        $scope.users.push($scope.inserted);
      };

      $scope.eliminarAerolinea = function(idPuerta) {
      var variable_entrega={"idPuerta":idPuerta};
      $http({
        method:'POST',
        url: globalBackendLink + '/puertas/eliminar',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function successCallback(response) {
        console.log("exito");
      },function errorCallback(response) {
        console.log('error en obtener puertas de '+globalBackendLink);
      });
      };
  
      editableOptions.theme = 'bs3';
      editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
      editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
  
      $scope.open = function (page, size) {
        $uibModal.open({
          animation: true,
          templateUrl: page,
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });
      };
      $scope.openProgressDialog = baProgressModal.open; 
	  $scope.seleccionarPuerta=function(puerta){
		  $scope.puertaseleccionada=puerta;
	  }
    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasNuevoCtrl', PuertasMangasZonasNuevoCtrl);
  
    /** @ngInject */
    function PuertasMangasZonasNuevoCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador nuevo');
      
      var contro = this;

      $scope.disabled = undefined;
      $scope.hols='hola';

      $scope.standardItem = {};
      $scope.standardSelectItems = [
        {label: 'Option 1', value: 1},
        {label: 'Option 2', value: 2},
        {label: 'Option 3', value: 3},
        {label: 'Option 4', value: 4}
      ];

    $scope.registrarPuertas=function(tipo,distancia,flujoPer,estado){
      var link_header='http://200.16.7.178:8080';
      var variable_entrega={"Tipo":tipo,"distanciaASalida": distancia,"flujoPersonas": flujoPer,"Estado": estado};
      // $http.post(link_header+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
      //   console.log('post usuario success');
      //   console.log(response);
      //   $state.go('agaport_gestion.usuarios');
      // });

      $http({
        url: link_header + '/puertas/insertar',
        method: 'POST',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function() {
        console.log('post puertas success');
        $state.go('agaport_gestion.puertas');
      },function(response){
        console.log('error POST');
        console.log(response);
      });

    }

  };

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('PuertasMangasZonasModificarCtrl', PuertasMangasZonasModificarCtrl);

    /** @ngInject */
    function PuertasMangasZonasModificarCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador modificar');
      
      var contro = this;

      $scope.disabled = undefined;
      $scope.hols='hola';

      $scope.standardItem = {};
      $scope.standardSelectItems = [
        {label: 'Option 1', value: 1},
        {label: 'Option 2', value: 2},
        {label: 'Option 3', value: 3},
        {label: 'Option 4', value: 4}
      ];
    }
  
  })();
  