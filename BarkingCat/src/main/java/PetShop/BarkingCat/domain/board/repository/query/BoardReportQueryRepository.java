package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.domain.board.dto.ReportBoardDetailResponse;
import PetShop.BarkingCat.domain.board.dto.ReportBoardResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QBoardReport.boardReport;

@Repository
public class BoardReportQueryRepository {
    private final JPAQueryFactory query;

    public BoardReportQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<ReportBoardResponse> findReportBoard(Pageable pageable){
        List<ReportBoardResponse> response = query.select(Projections.constructor(ReportBoardResponse.class,
                boardReport.reportCategory,
                boardReport.board.title))
                .from(boardReport)
                .where(
                        isNotDeleted()
                )
                .groupBy(boardReport.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardReport.createdDateTime.desc())
                .fetch();
        return new PageImpl<>(response, pageable, response.size());
    }

    public ReportBoardDetailResponse findDetailReportBoard(Long boardReportId){
        return query.select(Projections.constructor(ReportBoardDetailResponse.class,
                boardReport.id,
                boardReport.board.id,
                boardReport.reporterId,
                boardReport.reportCategory,
                boardReport.detail))
                .from(boardReport)
                .where(
                        isNotDeleted(),
                        boardReportIdEq(boardReportId)
                )
                .fetchFirst();
    }

    private BooleanExpression boardReportIdEq(Long boardReportId) {
        return boardReport.id.eq(boardReportId);
    }

    private BooleanExpression isNotDeleted() {
        return boardReport.deletedDateTime.isNull();
    }
}
