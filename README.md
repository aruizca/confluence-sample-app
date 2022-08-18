# confluence-sample-app

----------

This is a simple Confluence app that will be referenced by another Confluence app as a dependency app.

This app just contains a `{helloworld}` macro and page menu web item with a link to Google. This is just to quickly 
verify that is running correctly.

There is also an exported service so test that it can be used by the main app.

The main app is located in this other repo and the aim for this app is to technically assess and understand how OBR 
packaging works.

All is needed is packaging the app so that is available to the main app:

```shell
mvn clean install
```