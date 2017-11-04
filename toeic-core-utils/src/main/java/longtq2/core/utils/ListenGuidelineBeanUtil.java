package longtq2.core.utils;

import longtq2.core.dto.ListenGuidelineDTO;
import longtq2.core.persistence.enity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {
    public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity listenGuidelineEntity) {
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenguidelineId(listenGuidelineEntity.getListenguidelineId());
        dto.setContent(listenGuidelineEntity.getContent());
        dto.setTitle(listenGuidelineEntity.getTitle());
        dto.setImage(listenGuidelineEntity.getImage());
        dto.setCreatedDate(listenGuidelineEntity.getCreatedDate());
        dto.setModifileDate(listenGuidelineEntity.getModifileDate());

        return dto;
    }
    public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO listenGuidelineDTO) {
        ListenGuidelineEntity entity = new ListenGuidelineEntity();
        entity.setListenguidelineId(listenGuidelineDTO.getListenguidelineId());
        entity.setContent(listenGuidelineDTO.getContent());
        entity.setTitle(listenGuidelineDTO.getTitle());
        entity.setImage(listenGuidelineDTO.getImage());
        entity.setCreatedDate(listenGuidelineDTO.getCreatedDate());
        entity.setModifileDate(listenGuidelineDTO.getModifileDate());
        return entity;
    }
}
