document.addEventListener('DOMContentLoaded', () => {
    let mapContainer = document.getElementById('mapContainer');
    let map = L.map('map').setView([36.8065, 10.1815], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    let marker = L.marker([36.8065, 10.1815]).addTo(map)
        .bindPopup('Tunis, Tunisia')
        .openPopup();

    let currentField = null;

    function showCurrentLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                const coords = [position.coords.latitude, position.coords.longitude];
                map.setView(coords, 13);
                marker.setLatLng(coords);
            }, (error) => {
                console.error("Erreur lors de la récupération de la position : ", error);
            });
        } else {
            console.error("La géolocalisation n'est pas supportée par ce navigateur.");
        }
    }

    document.getElementById('departMapIcon').addEventListener('click', function() {
        currentField = 'depart';
        mapContainer.style.display = 'block';
        showCurrentLocation();
    });

    document.getElementById('destinationMapIcon').addEventListener('click', function() {
        currentField = 'destination';
        mapContainer.style.display = 'block';
        showCurrentLocation();
    });

    map.on('click', function(e) {
        let latlng = e.latlng;
        marker.setLatLng(latlng);
        fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${latlng.lat}&lon=${latlng.lng}`)
            .then(response => response.json())
            .then(data => {
                let displayNameParts = data.display_name.split(',');
                let placeName = displayNameParts[0];
                document.getElementById(currentField).value = placeName;
            })
            .catch(error => console.log('Erreur dans la récupération de l’adresse: ', error));
    });

    document.getElementById('closeMapButton').addEventListener('click', function() {
        mapContainer.style.display = 'none';
    });
});