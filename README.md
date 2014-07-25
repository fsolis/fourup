#fourup.com - making and breaking design decisions

There is a phase in making design decisions when alternatives are placed side-by-side for comparison. [fourup.com](http://fourup.com) is a tool for placing four Web resources into a single browser window.

>This kind of printing, where two pages are printed side-by-side on a piece of paper is called “two-up,” for the two pages facing up. This idea generalizes readily to any number of pages (though, of course, legibility goes down quickly as the number of pages goes up). In its general form, it is called “n-up.”
 >[link](http://www.tailrecursive.org/postscript/nup.html)

It works by encoding the resources to compare into the url:

    http://fourup.com/
      ?first=http://arstechnica.com
      &second=http://theverge.com
      &third=http://gizmodo.com
      &fourth=http://engadget.com


## References

[Two Up](http://www.tailrecursive.org/postscript/nup.html)
