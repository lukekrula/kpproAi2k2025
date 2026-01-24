#!/bin/bash
echo "=== Importuji ARES CSV data ==="

# Spolky
if [ -f /data/ares/spolky.csv ]; then
  mongoimport \
    --db appdb \
    --collection organizations \
    --type csv \
    --headerline \
    --file /data/ares/spolky.csv
fi

# Firmy
if [ -f /data/ares/firmy.csv ]; then
  mongoimport \
    --db appdb \
    --collection organizations \
    --type csv \
    --headerline \
    --file /data/ares/firmy.csv
fi

# Statní organizace
if [ -f /data/ares/statni_org.csv ]; then
  mongoimport \
    --db appdb \
    --collection organizations \
    --type csv \
    --headerline \
    --file /data/ares/statni_org.csv
fi

# Velký dataset subjektů podle IČO
if [ -f /data/ares/res_data_subjekty_ico.csv ]; then
  mongoimport \
    --db appdb \
    --collection organizations \
    --type csv \
    --headerline \
    --file /data/ares/res_data_subjekty_ico.csv
fi

echo "=== Import ARES CSV hotov ==="
