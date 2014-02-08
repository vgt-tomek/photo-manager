photo-manager
=============

Photo manager is console application I've created to speedup process of preparing new photos to fit my archivization scheme.

When I download new photo session from my camera, files look like this:

![Photo manager - before](http://blog.vgtworld.pl/wp-content/uploads/2013/12/photo-manager-files-before.jpg)

There are two steps, that can be easily automated.

First is renaming files to my naming template, which is: {year}-{month}-{day} - {number}.{extension}

"rename" action from photo-manager does exactly that:

![Photo manager - after](http://blog.vgtworld.pl/wp-content/uploads/2013/12/photo-manager-files-after.jpg)

Later I choose, which files to keep and which ones I don't need. When I delete them, only jpeg files are remove, but raw are still there. At this point second autometaed step is to remove raw files with missing coresponding jpeg.

Before:

![Photo manager - before](http://blog.vgtworld.pl/wp-content/uploads/2013/12/photo-manager-delete-before.jpg)

and after:

![Photo manager - after](http://blog.vgtworld.pl/wp-content/uploads/2013/12/photo-manager-delete-after.jpg)
