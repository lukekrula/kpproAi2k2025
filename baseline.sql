-- H2 2.3.232; 
;              
CREATE USER IF NOT EXISTS "SA" SALT '8fbcfec7eb63801e' HASH '9a46dbefaa433a2137648060270d6b2f346cf71ebac339e6575629f728d0bc79' ADMIN;          
CREATE CACHED TABLE "PUBLIC"."flyway_schema_history"(
    "installed_rank" INTEGER NOT NULL,
    "version" CHARACTER VARYING(50),
    "description" CHARACTER VARYING(200) NOT NULL,
    "type" CHARACTER VARYING(20) NOT NULL,
    "script" CHARACTER VARYING(1000) NOT NULL,
    "checksum" INTEGER,
    "installed_by" CHARACTER VARYING(100) NOT NULL,
    "installed_on" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "execution_time" INTEGER NOT NULL,
    "success" BOOLEAN NOT NULL
);           
ALTER TABLE "PUBLIC"."flyway_schema_history" ADD CONSTRAINT "PUBLIC"."flyway_schema_history_pk" PRIMARY KEY("installed_rank"); 
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.flyway_schema_history;    
INSERT INTO "PUBLIC"."flyway_schema_history" VALUES
(-1, NULL, '<< Flyway Schema History table created >>', 'TABLE', '', NULL, 'SA', TIMESTAMP '2026-01-09 21:16:20.629333', 0, TRUE);         
CREATE INDEX "PUBLIC"."flyway_schema_history_s_idx" ON "PUBLIC"."flyway_schema_history"("success" NULLS FIRST);
