var geographic = new OpenLayers.Projection("EPSG:4326");
		//var mercator = new OpenLayers.Projection("EPSG:900913");

		var world = new OpenLayers.Bounds(-180, -89, 180, 89);//.transform(geographic, mercator);
		var center = new OpenLayers.LonLat(-104.98, 39.76);//.transform(geographic, mercator);

		var options = {
			projection : geographic,
			units : "m",
			maxExtent : world
		};
		var map = new OpenLayers.Map("map-id", options);

		var osm = new OpenLayers.Layer.OSM();
		map.addLayer(osm);

		var mouseControl = new OpenLayers.Control.MousePosition();
		mouseControl.projection = geographic;
// 		map.events.register("mousemove", map, function(e) {
//   		var pixel = new OpenLayers.Pixel(e.xy.x,e.xy.y);
//   		var lonlat = map.getLonLatFromPixel(pixel);
//   		var lonlatGCS = OpenLayers.Layer.SphericalMercator.inverseMercator(lonlat.lon, lonlat.lat);
// 		});
// 		map.addControl(mouseControl);

    map.addControl(new OpenLayers.Control.MousePosition( {id: "ll_mouse", formatOutput: formatLonlats} ));
		//map.setCenter(center, 9);
		map.zoomToMaxExtent();

	    function formatLonlats(lonLat) {
	        var lat = lonLat.lat;
	        var lon = lonLat.lon;
	        var ns = OpenLayers.Util.getFormattedLonLat(lat);
	        var ew = OpenLayers.Util.getFormattedLonLat(lon,'lon');
	        //return ns + ', ' + ew + ' (' + (Math.round(lat * 10000) / 10000) + ', ' + (Math.round(lon * 10000) / 10000) + ')';
	        return lat.toString() +" , "+ lon.toString();
	    }
