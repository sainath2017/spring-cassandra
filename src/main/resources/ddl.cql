
CREATE KEYSPACE IF NOT EXISTS sample
    WITH replication = {'class': 'NetworkTopologyStrategy', 'replication_factor': '3'};

#----------------------------------------------------------------------------
# Tables
#----------------------------------------------------------------------------

USE sample;