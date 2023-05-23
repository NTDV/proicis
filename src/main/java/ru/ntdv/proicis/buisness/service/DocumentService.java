package ru.ntdv.proicis.buisness.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import ru.ntdv.proicis.buisness.model.File;
import ru.ntdv.proicis.buisness.model.Skill;
import ru.ntdv.proicis.crud.model.Season;
import ru.ntdv.proicis.crud.model.Theme;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@AllArgsConstructor
public
class DocumentService {
private final StorageService storageService;

public
XMLSlideShow getThemesPresentationFrom(final Iterable<Theme> themes, final Season season, final FileInputStream templatePotx)
throws IOException {
    val pptx = new XMLSlideShow(templatePotx);
    pptx.getPackage().replaceContentType(XSLFRelation.PRESENTATIONML_TEMPLATE.getContentType(),
                                         XSLFRelation.MAIN.getContentType());
    val master = pptx.getSlideMasters().get(0);
    val firstSlide = pptx.createSlide(master.getLayout("title_slide"));
    firstSlide.getPlaceholder(0).setText(season.getTitle());
    firstSlide.getPlaceholder(1).setText("Темы проектов");

    for (val theme : themes) {
        val slide = pptx.createSlide(master.getLayout("theme_slide"));
        slide.getPlaceholder(0).setText(theme.getTitle());
        slide.getPlaceholder(1).setText(theme.getDescription());
        slide.getPlaceholder(2).setText(String.valueOf(theme.getHardness().ordinal()));
        slide.getPlaceholder(3).setText(theme.getSkills().stream().map(Skill::getTitle).collect(Collectors.joining(", ")));

        val pic = slide.getPlaceholder(4);
        val anchor = pic.getAnchor();
        val pictureFile = new File(storageService.getRootPath(), theme.getPresentationSlide());
        val pictureData = pptx.addPicture(pictureFile.getInputStream(), pictureFile.getPictureType());
        val picture = slide.createPicture(pictureData);
        slide.removeShape(pic);
        picture.setAnchor(anchor);
    }
    return pptx;
}
}
