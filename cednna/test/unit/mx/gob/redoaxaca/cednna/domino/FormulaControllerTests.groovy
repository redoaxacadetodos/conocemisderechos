package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(FormulaController)
@Mock(Formula)
class FormulaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/formula/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.formulaInstanceList.size() == 0
        assert model.formulaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.formulaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.formulaInstance != null
        assert view == '/formula/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/formula/show/1'
        assert controller.flash.message != null
        assert Formula.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/formula/list'

        populateValidParams(params)
        def formula = new Formula(params)

        assert formula.save() != null

        params.id = formula.id

        def model = controller.show()

        assert model.formulaInstance == formula
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/formula/list'

        populateValidParams(params)
        def formula = new Formula(params)

        assert formula.save() != null

        params.id = formula.id

        def model = controller.edit()

        assert model.formulaInstance == formula
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/formula/list'

        response.reset()

        populateValidParams(params)
        def formula = new Formula(params)

        assert formula.save() != null

        // test invalid parameters in update
        params.id = formula.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/formula/edit"
        assert model.formulaInstance != null

        formula.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/formula/show/$formula.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        formula.clearErrors()

        populateValidParams(params)
        params.id = formula.id
        params.version = -1
        controller.update()

        assert view == "/formula/edit"
        assert model.formulaInstance != null
        assert model.formulaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/formula/list'

        response.reset()

        populateValidParams(params)
        def formula = new Formula(params)

        assert formula.save() != null
        assert Formula.count() == 1

        params.id = formula.id

        controller.delete()

        assert Formula.count() == 0
        assert Formula.get(formula.id) == null
        assert response.redirectedUrl == '/formula/list'
    }
}
