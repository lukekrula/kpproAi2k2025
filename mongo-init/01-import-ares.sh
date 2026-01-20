#!/bin/bash
echo "Importuji ARES CSV data..."

mongoimport \
  --db appdb \
  --collection organizations \
  --type csv \
  --headerline \
  --file /data/ares/spolky.csv

mongoimport \
  --db appdb \
  --collection organizations \
  --type csv \
  --headerline \
  --file /data/ares/firmy.csv

mongoimport \
  --db appdb \
  --collection organizations \
  --type csv \
  --headerline \
  --file /data/ares/statni_org.csv

echo "Import ARES CSV hotov."
