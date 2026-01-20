#!/bin/bash
echo "Importuji GeoJSON památky a území..."

mongoimport \
  --db appdb \
  --collection sites \
  --file /data/geojson/pamatky.geojson \
  --jsonArray

mongoimport \
  --db appdb \
  --collection territories \
  --file /data/geojson/uzemi.geojson \
  --jsonArray

echo "Import GeoJSON hotov."
