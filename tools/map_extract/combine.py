import os
from PIL import Image, ImageFile
from argparse import ArgumentParser, ArgumentTypeError
from shutil import copyfile


def get_slices(image, horizontal):
    slices = list()
    width, height = image.size
    dimension = height if horizontal else width

    for i in range(dimension):
        box = (0, i, width, i+1) if horizontal else (i, 250, i+1, 500)
        strip = image.crop(box)
        sequence = list(strip.getdata())
        slices.append(sequence)
    return slices


def get_matching_index(source, target):
    for source_index, source_value in enumerate(source):
        for target_index, target_value in enumerate(target):
            if source_value == target_value:
                return source_index
    return -1


def write_image(input_image, output_path):
    if os.path.exists(output_path):
        os.remove(output_path)
    input_image.save(output_path, "PNG")


def combine_image(image_1, image_2, horizontal):
    width_1, height_1 = image_1.size
    width_2, height_2 = image_2.size

    new_width = max(width_1, width_2) if horizontal else width_1 + width_2
    new_height = height_1 + height_2 if horizontal else max(height_1, height_2)

    offset_x = 0 if horizontal else width_1
    offset_y = height_1 if horizontal else 0

    new_image = Image.new('RGB', (new_width, new_height))
    new_image.paste(image_1, (0, 0))
    new_image.paste(image_2, (offset_x, offset_y))

    return new_image


def crop_image_to(image, dimension, horizontal):
    width, height = image.size
    new_width = width if horizontal else dimension
    new_height = dimension if horizontal else height
    return image.crop(box=(0, 0, new_width, new_height))


def crop_image_from(image, dimension, horizontal):
    width, height = image.size
    new_x = 0 if horizontal else dimension
    new_y = dimension if horizontal else 0
    return image.crop(box=(new_x, new_y, width, height))


def combine(input_path_1, input_path_2, output_path, horizontal):
    image_1 = Image.open(input_path_1).convert("RGB")
    image_2 = Image.open(input_path_2).convert("RGB")

    slices_1 = get_slices(image_1, horizontal)
    slices_2 = get_slices(image_2, horizontal)

    matching_line_1 = get_matching_index(slices_1, slices_2)
    matching_line_2 = get_matching_index(slices_2, slices_1)

    matching_line = max(matching_line_1, matching_line_2)
    below = matching_line_1 > matching_line_2

    if matching_line != -1:
        name_1 = os.path.basename(input_path_1)
        name_2 = os.path.basename(input_path_2)
        print("Image", name_1, "matches", name_2, "on line", matching_line)

        # if below:
        cropped_image_1 = crop_image_to(image_1, matching_line_1, horizontal)
        cropped_image_2 = crop_image_from(image_2, matching_line_2, horizontal)

        combined_image = combine_image(cropped_image_1, cropped_image_2, horizontal)
        write_image(combined_image, output_path)
        # else:
        #     cropped_image = crop_image_to(image_2, matching_line, horizontal)
        #     combined_image = combine_image(cropped_image, image_1, horizontal)
        #     write_image(combined_image, output_path)

        return True

    return False


def string_to_boolean(v):
    if v.lower() in ('yes', 'true', 't', 'y', '1'):
        return True
    elif v.lower() in ('no', 'false', 'f', 'n', '0'):
        return False
    else:
        raise ArgumentTypeError('Boolean value expected.')


def main():
    ImageFile.LOAD_TRUNCATED_IMAGES = True

    parser = ArgumentParser()
    parser.add_argument("-i", "--input", dest="input", default="captures",
                        help="The directory containing images to combine")
    parser.add_argument("-o", "--output", dest="output", default="combined",
                        help="The directory where the combined files will be stored")
    parser.add_argument("-b", "--begin", dest="begin", type=int, default=0)
    parser.add_argument("-e", "--end", dest="end", type=int, default=10)
    parser.add_argument("-p", "--prefix", dest="prefix", type=str, default="capture")
    parser.add_argument("-s", "--suffix", dest="suffix", type=str, default=".png")
    parser.add_argument("-z", "--horizontal", dest="horizontal", type=string_to_boolean, default=True)
    arguments = parser.parse_args()

    input_dir = arguments.input
    output_dir = arguments.output
    output_prefix = "combined"
    output_suffix = ".png"
    output_counter = 1

    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    output_image = os.path.join(output_dir, output_prefix + str(output_counter) + output_suffix)
    initial_image = os.path.join(input_dir, arguments.prefix + str(arguments.begin) + arguments.suffix)
    copyfile(initial_image, output_image)

    for i in range(arguments.begin + 1, arguments.end + 1):
        input_image = os.path.join(input_dir, arguments.prefix + str(i) + arguments.suffix)

        if os.path.exists(input_image) and not combine(output_image, input_image, output_image, arguments.horizontal):
            print("Unable to merge: " + str(input_image))
            output_counter += 1
            output_image = os.path.join(output_dir, output_prefix + str(output_counter) + output_suffix)
            copyfile(input_image, output_image)


if __name__ == "__main__":
    main()
