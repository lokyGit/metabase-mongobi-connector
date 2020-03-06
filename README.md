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

## Another Approach to build a custom metabase driver is as below

## Installing metabase-core locally
The dependency on metabase-core makes all namespaces that are part of the core Metabase project (e.g. metabase.driver) available for use in the driver itself. By putting this dependency in a provided profile, lein uberjar won't include that dependency in the built driver.

Note that Metabase is not currently available in Clojars or other plugin repositories -- you'll have to install it locally before working on a driver. You can do this by running
```
lein install-for-building-drivers
```
from the root of the core Metabase repository. For now, metabase-core has one version -- 1.0.0-SNAPSHOT -- so this is what your driver should specify. As APIs get locked down in the near future and we ship a Metabase 1.0 release, we'll ship real [metabase-core "1.0.0"] (and so forth) dependencies, and most likely publish them on Clojars, meaning you'll be able to skip this step; for now, stick to [metabase-core "1.0.0-SNAPSHOT"]. I'll update this guide when this changes.

## Building a driver plugin shipped as part of the core Metabase repo
A helpful script is included as part of Metabase to build drivers packaged this way:
```
./bin/build-driver.sh mongobi 
```
This will take care of everything and copy the resulting file to ./resources/modules/mongo.metabase-driver.jar. You have to keep the mongobi driver folder in metabase/modules/drivers directory.

## You can also build the JAR using
```
LEIN_SNAPSHOTS_IN_RELEASE=true lein uberjar
```
from the modules/drivers/mongo directory; you'll have to copy it into resources/modules yourself to have it included with the Metabase uberjar if you're building it locally.
