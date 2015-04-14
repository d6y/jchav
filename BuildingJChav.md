# Compiling #

cd into the
`etc` directory and run `ant`.  This will compile the source, run unit tests and create a `dist` folder that contains the compiled distribution of JChav.

# Running an example #

cd into the `docs/example` folder.

Create a _username_.properties file, where _username_ is your username on your computer.  This file is loaded by the example ant file to pick up settings for the location of JMeter and your compiled distribution.  Add the following two lines to the _username_.properties file:

```
jmeter.install.dir=/path/to/jakarta-jmeter-2.2/
jchav.libs.dir=/path/to/jchav/dist
```

Run `ant -f build-example.xml`

This executes the `docs/examples/digwalk.jmx` JMeter script and produces reports in `docs/examples/digjchavresults/` folder.  Open them in your browser to see the results.

You will have one data point. Run `and -f build-example.xml` again, the same JMeter script will run, and you'll now have two data points on the graphs.

# Releasing JChav #

To build a ZIP release of the project, cd back into the `etc` folder, pick a release version number (1.2.0 in this example) and run the following:

```
ant -Drelease=1.2.0 release
```

This will create a zip file in `releases/`.