If experiencing issues matching map image with tile set due to subtle colour differences, reduce colour palette using the following:
  Gimp -> Image -> Mode -> Indexed -> Custom Palette -> Muted

To run execute the following:
  pipenv install
  pipenv run python MapWriter.py 32 32 map.png terrain.png
