#!/bin/bash
echo "=== Importuji ARES CSV data ==="

for file in /data/ares/*.csv; do
  if [ -f "$file" ]; then
    echo "Importuji $file"
    mongoimport \
      --db kppro \
      --collection organizations \
      --type csv \
      --headerline \
      --file "$file" \
      --username root \
      --password secret \
      --authenticationDatabase admin
  fi
done

echo "=== Import hotov ==="
