# Metabase MongoDB BI Connector Driver

Please see [Writing A Driver](https://github.com/metabase/metabase/wiki/Writing-A-Driver) for general instructions on how to implement a custom Metabase driver.

## Building

You will need to have the proper [prerequisites](https://github.com/metabase/metabase/blob/master/docs/developers-guide.md#install-prerequisites) installed prior to building the driver, specifically JDK 8 and Leiningen.

### Install `metabase-core` locally

Clone the Metabase project and install the necessary dependencies locally.

```shell
git clone https://github.com/metabase/metabase.git
cd metabase
lein install-for-building-drivers
```

### Build the Driver

(in the root of this project)

```shell
DEBUG=1 LEIN_SNAPSHOTS_IN_RELEASE=true lein uberjar
```

This will create a file named `mongobi.metabase-driver.jar` in the `target/uberjar` directory.

## Installing

Copy the `mongobi.metabase-driver.jar` file to the the `plugins/` directory of wherever you are running Metabase from.

You should see a message on startup similar to:

```
02-14 08:02:33 DEBUG plugins.lazy-loaded-driver :: Registering lazy loading driver :mongobi...
02-14 08:02:33 INFO driver.impl :: Registered driver :mongobi (parents: [:mysql]) 🚚
```

## Adding a Database

On the "Add Database" page in the Metabase Admin, you should see a new database type: "MongoDB BI Connector".

Select that and fill out the relevant details as provided in your MongoDB Atlas interface.

You can leave the "Additional JDBC options" section blank. SSL is enabled by default.