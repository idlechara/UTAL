$(document).ready(function() {
        
     	// Html del popup con pestañas de jquery
//         function prepararFicha(ciudad) {
//             var div = $("<div>");
//             var contenido = $("<div id='popupConTabs' style='font-size:9px;'>");
//             var titulos = $("<ul>" +
//                                 "<li><a href='#tab-1'>Atributos</a></li>" +
//                                 "<li><a href='#tab-2'>Wikipedia</a></li>" +
//                             "</ul>");
//             contenido.append(titulos);

//             // Pestaña 1
//             var p1 = $("<div id='tab-1'>");
//             p1.append($("<p>" +
//                             "<b>Nombre</b>: " + ciudad.attributes.Nombre + "<br/>" +
//                             "<b>Habs.</b>: " + ciudad.attributes.Habitantes +
//                         "</p>"));
//             contenido.append(p1);

//             // Pestaña 2
//             var p2 = $("<div id='tab-2'>");
//             p2.append($("<p><a href='" + ciudad.attributes.Wikipedia + "'>Wikipedia</b></p>"));
//             contenido.append(p2);

//             div.append(contenido);
//             return div.html();
//         }
        
     	
     	
     	
     	
     	// Creación del mapa y encuadre inicial
        var WGS84 = new OpenLayers.Projection('EPSG:4326');
        var MERCATOR = new OpenLayers.Projection('EPSG:900913');
        var utal = new OpenLayers.Bounds(-71.229806, -35.001777, -71.229768, -35.001742).transform(WGS84, MERCATOR);
        var mapa = new OpenLayers.Map({
            div: "mapa",
            controls: [
                new OpenLayers.Control.Navigation(
                    {dragPanOptions: {enableKinetic: true}}
                )
            ]
        });
        mapa.addLayer(new OpenLayers.Layer.OSM());
        mapa.zoomToExtent(utal);

        // Creación de una capa vectorial con 1 registro puntual
        var oficinas = new OpenLayers.Layer.Vector("Oficinas");
        mapa.addLayer(oficinas);
        //var geometria = new OpenLayers.Geometry.Point(-71.229806, -35.001777).transform(WGS84, MERCATOR);
        
        //aca se debe añadir una capa para poder almacenar los datos,
        //estas capas son guardadas en un archivo osm, el cual puede ser generado usando cualquier
        //programa de edicion.
//         var capaUtal = new OpenLayers.Layer.Vector(
//         		"Capa edificios Utalca", 
        		
//         		{	
//         			protocol: new OpenLayers.Protocol.HTTP({
//         				url: "http://172.17.32.211/mapExamples/jquerryGeoDat/edificios.osm";
//         				format: new OpenLayers.Format.OSM({extractStyiles: true});
//         			}),
//         			strategies: [new OpenLayers.Strategy.Fixed()],
//         			Projection: wgs
//         		}
//         );
        
//         mapa.addLayer(capaUtal);

                var capaUtal = new OpenLayers.Layer.Vector("CapaUtal", {
                    strategies: [new OpenLayers.Strategy.Fixed()],
                    protocol: new OpenLayers.Protocol.HTTP({
                        url: "edificios.osm",   //<-- relative or absolute URL to your .osm file
                        format: new OpenLayers.Format.OSM()
                    }),
                    projection: new OpenLayers.Projection("EPSG:4326"),
                	transitionEffect: 'resize'
                });
        mapa.addLayer(capaUtal);
        
        
//        var capaCurico = new OpenLayers.Layer.Vector("capaCurico", {
//            strategies: [new OpenLayers.Strategy.Fixed()],
//            protocol: new OpenLayers.Protocol.HTTP({
//                url: "mapaCurico.osm",   //<-- relative or absolute URL to your .osm file
//                format: new OpenLayers.Format.OSM()
//            }),
//            projection: new OpenLayers.Projection("EPSG:4326"),
//        	transitionEffect: 'resize'
//        });
//		mapa.addLayer(capaCurico);

//         var popup = new OpenLayers.Popup("chicken",
//                 new OpenLayers.LonLat(-35,-71),
//                 new OpenLayers.Size(200,200),
//                 "example popup",
//                 true);
// 		mapa.addPopup(popup);
        
        
//         var puntos = []; //se define un arreglo de puntos.
        
//         var p1 = new OpenLayers.Geometry.Point( wi-71.229945, -35.001637).transform(WGS84, MERCATOR);
//         var p2 = new OpenLayers.Geometry.Point(-71.229945, -35.001874).transform(WGS84, MERCATOR);
//         var p3 = new OpenLayers.Geometry.Point(-71.22964, -35.001874).transform(WGS84, MERCATOR);
//         var p4 = new OpenLayers.Geometry.Point(-71.22964, -35.001637).transform(WGS84, MERCATOR);

//         puntos.push(p1);
//         puntos.push(p2);
//         puntos.push(p3);
//         puntos.push(p4);
        
//         var geometria = new OpenLayers.Geometry.LinearRing(puntos);
        //var polygonFeature = new OpenLayers.Feature.Vector(geometria, null, style_green);
        //oficinas.addFeatures([polygonFeature]);
        
//         var atributos = {
//             Nombre: 'Oficina',
//             Habitantes: 18,
//             Wikipedia: 'http://es.wikipedia.org/wiki/oficina'
//         };
//         var oficina = new OpenLayers.Feature.Vector(geometria, atributos);
//         oficinas.addFeatures([oficina]);

        // Creación del control de selección
//         var controlSeleccion = new OpenLayers.Control.SelectFeature(capaUtal);
        var controlSeleccion = new OpenLayers.Control.SelectFeature(capaUtal,
        		{onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
        mapa.addControl(controlSeleccion);
        controlSeleccion.activate();
        
        var selectedFeature, popup;

        function onPopupClose(evt) {
            selectControl.unselect(selectedFeature);
        }
        function onFeatureSelect(feature) {
            selectedFeature = feature;
            popup = new OpenLayers.Popup.FramedCloud("chicken", 
                                     feature.geometry.getBounds().getCenterLonLat(),
                                     null,
                                     "<div style='font-size:.8em'>Feature: " + feature.id +"<br>Area: " + feature.geometry.getArea()+ "<br>Center coord: " + feature.geometry.getBounds().getCenterLonLat() 
                                     +"<br> Aca le toca al garrao aplicarse, con la base<br>de datos... aca le puedo entregar como <br>identificador el centro de la figura en<br>cuestion, para que me entrege la info.</div>",
                                     null, true, onPopupClose);
            feature.popup = popup;
            mapa.addPopup(popup);
        }
        function onFeatureUnselect(feature) {
            mapa.removePopup(feature.popup);
            feature.popup.destroy();
            feature.popup = null;
        }    
//      // Mostrar el popup al seleccionar la oficina
//         var popup;
//         capaUtal.events.on({
//             "featureselected": function(e) {

//             	var oficina = e.feature;
// //                 alert(oficina.attributes.id);
                
// //             	Alert("Seleccionado");
// //                 var oficina = e.feature;
// //                 var html = prepararFicha(oficina);
//                 popup = new OpenLayers.Popup.FramedCloud(
//                     "Info oficina",
//                     oficina.geometry.getBounds().getCenterLonLat(),
//                     null,
//                     "<div style='font-size:.8em'>Feature: " + oficina.id +"<br>Area: " + oficina.geometry.getArea()+"</div>",
//                     null, true, function() {
//                         controlSeleccion.unselect(oficina);
//                 });
// //                 popup.minSize = new OpenLayers.Size(300, 100);
// //                 oficina.popup = popup;
// //                 mapa.addPopup(popup);
// //                 $("#popupConTabs").tabs(); /* << Creacion de Tabs */
//             },
//             "featureunselected": function(e) {
                
// //                 var ciudad = e.feature;
// //                 mapa.removePopup(oficina.popup);
// //                 oficina.popup.destroy();
// //                 oficina.popup = null;
//             }
//         });

        
        
});