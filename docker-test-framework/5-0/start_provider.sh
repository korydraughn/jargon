#! /bin/bash

# Start the Postgres database.
service postgresql start
counter=0
until pg_isready -q
do
    sleep 1
    ((counter += 1))
done
echo Postgres took approximately $counter seconds to fully start ...

# Rsyslog must be started before iRODS so that the log messages
# are written to the correct file.
rsyslogd

# Set up iRODS.
python3 /var/lib/irods/scripts/setup_irods.py < /var/lib/irods/packaging/localhost_setup_postgres.input
su - irods -c 'irodsServer -d'

# Wait until iRODS server is ready for connections.
until nc -z localhost 1247; do
    echo 'Waiting for iRODS Provider to accept connections before creating test users, etc. ...'
    sleep 1
done

echo Running Test Setup Script
su irods -c '/testsetup-consortium.sh'
echo Completed Test Setup Script

echo 'iRODS Provider is ready.'

# Keep container running if the test fails.
sleep 2147483647d
