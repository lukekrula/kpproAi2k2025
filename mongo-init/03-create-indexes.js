print("=== Vytvářím indexy ===");

// Geo index pro památky a přírodní objekty
db.sites.createIndex({ location: "2dsphere" });

// Geo index pro organizace (pokud mají souřadnice)
db.organizations.createIndex({ location: "2dsphere" });

// Indexy na regiony
db.sites.createIndex({ regionCode: 1 });
db.organizations.createIndex({ "address.regionCode": 1 });
db.territories.createIndex({ regionCode: 1 });

// Index na typ památky
db.sites.createIndex({ type: 1 });

// Unikátní index na IČO (ne všude je unikátní, proto unique: false)
db.organizations.createIndex({ ico: 1 }, { unique: false });

// Index na název památky
db.sites.createIndex({ name: "text" });

// Index na název organizace
db.organizations.createIndex({ name: "text" });

print("=== Indexy vytvořeny ===");
