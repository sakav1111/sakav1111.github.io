The XML Tree Structure
----------------------

![DOM node tree](https://www.w3schools.com/xml/nodetree.gif)

* * * * *

An Example XML Document
-----------------------

The image above represents books in this XML:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<bookstore>
  <book category="cooking">
    <title lang="en">Everyday Italian</title>
    <author>Giada De Laurentiis</author>
    <year>2005</year>
    <price>30.00</price>
  </book>
  <book category="children">
    <title lang="en">Harry Potter</title>
    <author>J K. Rowling</author>
    <year>2005</year>
    <price>29.99</price>
  </book>
  <book category="web">
    <title lang="en">Learning XML</title>
    <author>Erik T. Ray</author>
    <year>2003</year>
    <price>39.95</price>
  </book>
</bookstore>
```

What is an XML Element?
-----------------------

An XML element is everything from (including) the element's start tag to (including) the element's end tag.

<price>29.99</price>

An element can contain:

-   text
-   attributes
-   other elements
-   or a mix of the above



XML Naming Rules
----------------
XML elements must follow these naming rules:

-   Element names are case-sensitive
-   Element names must start with a letter or underscore
-   Element names cannot start with the letters xml (or XML, or Xml, etc)
-   Element names can contain letters, digits, hyphens, underscores, and periods
-   Element names cannot contain spaces

Any name can be used, no words are reserved (except xml).


XML Namespaces - The xmlns Attribute
------------------------------------

When using prefixes in XML, a namespace for the prefix must be defined.

The namespace can be defined by an xmlns attribute in the start tag of an element.

The namespace declaration has the following syntax. xmlns:*prefix*="*URI*".

<root>

<h:table xmlns:h="http://www.w3.org/TR/html4/">\
  <h:tr>\
    <h:td>Apples</h:td>\
    <h:td>Bananas</h:td>\
  </h:tr>\
</h:table>

<f:table xmlns:f="https://www.w3schools.com/furniture">\
  <f:name>African Coffee Table</f:name>\
  <f:width>80</f:width>\
  <f:length>120</f:length>\
</f:table>

</root>

In the example above:

The xmlns attribute in the first <table> element gives the h: prefix a qualified namespace.

The xmlns attribute in the second <table> element gives the f: prefix a qualified namespace.

When a namespace is defined for an element, all child elements with the same prefix are associated with the same namespace.

Namespaces can also be declared in the XML root element:
