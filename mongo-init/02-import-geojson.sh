#!/bin/bash
echo "=== Importuji GeoJSON data ==="

# Kulturní památky – bodové objekty
if [ -f /data/geojson/kulturni_pamatky_bodove_objekty.geojson ]; then
  mongoimport \
    --db appdb \
    --collection sites \
    --file /data/geojson/kulturni_pamatky_bodove_objekty.geojson \
    --jsonArray
fi

# Kulturní památky – plošné objekty
if [ -f /data/geojson/kulturni_pamatky_plosne_objekty.geojson ]; then
  mongoimport \
    --db appdb \
    --collection sites \
    --file /data/geojson/kulturni_pamatky_plosne_objekty.geojson \
    --jsonArray
fi

# Přírodní rezervace
if [ -f /data/geojson/prirodni_rezervace.geojson ]; then
  mongoimport \
    --db appdb \
    --collection sites \
    --file /data/geojson/prirodni_rezervace.geojson \
    --jsonArray
fi

# Technické památky
if [ -f /data/geojson/Technicke_pamatky.geojson ]; then
  mongoimport \
    --db appdb \
    --collection sites \
    --file /data/geojson/Technicke_pamatky.geojson \
    --jsonArray
fi

# Ostatní historické památky
if [ -f /data/geojson/ostatni_historicke_pamatky_khk.geojson ]; then
  mongoimport \
    --db appdb \
    --collection sites \
    --file /data/geojson/ostatni_historicke_pamatky_khk.geojson \
    --jsonArray
fi

# Ptačí oblasti
if [ -f /data/geojson/ptaci_oblasti.geojson ]; then
  mongoimport \
    --db appdb \
    --collection territories \
    --file /data/geojson/ptaci_oblasti.geojson \
    --jsonArray
fi

# Území obcí (CSV → JSON import)
if [ -f /data/geojson/uzemi_obci_60.csv ]; then
  mongoimport \
    --db appdb \
    --collection territories \
    --type csv \
    --headerline \
    --file /data/geojson/uzemi_obci_60.csv
fi

echo "=== Import GeoJSON hotov ==="
