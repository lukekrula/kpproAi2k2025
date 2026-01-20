print("Vytvářím indexy...");

// Geo index pro památky
db.sites.createIndex({ location: "2dsphere" });

// Geo index pro organizace
db.organizations.createIndex({ location: "2dsphere" });

// Index na region
db.sites.createIndex({ regionCode: 1 });
db.organizations.createIndex({ "address.regionCode": 1 });

// Index na typ památky
db.sites.createIndex({ type: 1 });

// Unikátní index na IČO
db.organizations.createIndex({ ico: 1 }, { unique: false });

print("Indexy vytvořeny.");
